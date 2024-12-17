package com.example.demo.service;

import com.example.demo.controller.request.UserRegisterCommand;
import com.example.demo.entity.*;
import com.example.demo.exception.UserException;
import com.example.demo.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PlayerTypeRepo playerTypeRepo;
    private final PlayerInfoRepository playerInfoRepository;
    private final PlayerTimeTableRepo playerTimeTableRepo;
    private final LocationRepo locationRepo;

    public UserService(UserRepository userRepository, PlayerTypeRepo playerTypeRepo,
                       PlayerInfoRepository playerInfoRepository,
                       PlayerTimeTableRepo playerTimeTableRepo, LocationRepo locationRepo) {
        this.userRepository = userRepository;
        this.playerTypeRepo = playerTypeRepo;
        this.playerInfoRepository = playerInfoRepository;
        this.playerTimeTableRepo = playerTimeTableRepo;
        this.locationRepo = locationRepo;
    }

    public User addUser(UserRegisterCommand command) {
        try {
            User user = new User();
            user.setAge(command.getAge());
            user.setName(command.getName());
            user.setEmail(command.getEmail());
            user.setUserType(command.getUserType());
            user.setPhoneNumber(command.getPhoneNumber());
            User savedUser = userRepository.save(user);
            if (command.getPlayerGameInfo() != null) {
                PlayerGameInfo playerGameInfo = command.getPlayerGameInfo();
                playerGameInfo.setUser(savedUser);
                PlayerGameInfo gameInfo = playerInfoRepository.save(playerGameInfo);

                List<PlayerType> playerTypes = playerGameInfo.getPlayerTypes();
                if (playerTypes != null && !playerTypes.isEmpty()) {
                    playerTypes.forEach(playerType -> playerType.setPlayerGameInfo(gameInfo));
                    playerTypeRepo.saveAll(playerTypes);
                }

                List<PlayerTimeTable> playerTimeTables = playerGameInfo.getPlayerTimeTables();
                if (playerTimeTables != null && !playerTimeTables.isEmpty()) {
                    playerTimeTables.forEach(playerTimeTable -> playerTimeTable.setPlayerGameInfo(gameInfo));
                    playerTimeTableRepo.saveAll(playerTimeTables);
                }

                List<Location> preferLocations = playerGameInfo.getPreferLocations();
                if(preferLocations != null && !preferLocations.isEmpty()) {
                    preferLocations.forEach(location -> location.setPlayerGameInfo(gameInfo));
                    locationRepo.saveAll(locationRepo.findAll());
                }

                savedUser.setPlayerGameInfo(command.getPlayerGameInfo());
            }
            return savedUser;
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new UserException("user phoneNumber has been registered");
        }
    }

    public User getUser(String userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserException("The user id was not found"));
    }

    public User getUserPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber).orElseThrow(() -> new UserException("The user id was not found"));
    }
}
