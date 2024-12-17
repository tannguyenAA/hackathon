package com.example.demo.service;

import com.example.demo.controller.request.*;
import com.example.demo.entity.*;
import com.example.demo.entityenum.FieldServiceEnum;
import com.example.demo.entityenum.PitchTypeEnum;
import com.example.demo.exception.UserException;
import com.example.demo.repository.*;
import com.example.demo.util.Util;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FieldService {
    private final FieldRepo fieldRepository;
    private final MediaFileRepo mediaFileRepo;
    private final FieldServiceRepo fieldServiceRepo;
    private final PitchTimeSlotRepo pitchTimeSlotRepo;
    private final PitchRepo pitchRepo;
    private final PitchTypeRepo pitchTypeRepo;
    private final GameRepo gameRepo;
    private final CustomRepo customRepo;
    private final UserRepository userRepo;
    private final GameRegisterRepo gameRegisterRepo;

    public FieldService(FieldRepo fieldRepository, MediaFileRepo mediaFileRepo, FieldServiceRepo fieldServiceRepo, PitchTimeSlotRepo pitchTimeSlotRepo, PitchRepo pitchRepo, PitchTypeRepo pitchTypeRepo, GameRepo gameRepo, CustomRepo customRepo, UserRepository userRepo, GameRegisterRepo gameRegisterRepo) {
        this.fieldRepository = fieldRepository;
        this.fieldServiceRepo = fieldServiceRepo;
        this.mediaFileRepo = mediaFileRepo;
        this.pitchTimeSlotRepo = pitchTimeSlotRepo;
        this.pitchRepo = pitchRepo;
        this.pitchTypeRepo = pitchTypeRepo;
        this.gameRepo = gameRepo;
        this.customRepo = customRepo;
        this.userRepo = userRepo;
        this.gameRegisterRepo = gameRegisterRepo;
    }

    public Field createField(String userId, FieldCreateCommand fieldCreateCommand) {
        Field field = new Field();
        field.setUserId(userId);
        field.setCity(fieldCreateCommand.getCity());
        field.setDescription(fieldCreateCommand.getDescription());
        field.setFieldName(fieldCreateCommand.getFieldName());
        field.setAddress(fieldCreateCommand.getAddress());
        field.setWard(fieldCreateCommand.getWard());
        field.setDistrict(fieldCreateCommand.getDistrict());

        Field save = fieldRepository.save(field);
        if (fieldCreateCommand.getService() != null) {
            List<FiledService> services = createService(save, fieldCreateCommand.getService());
            save.setService(services);
        }
        List<MediaFile> mediaFile = createMediaFileField(save);
        save.setMediaFiles(mediaFile);
        return save;
    }

    private List<MediaFile> createMediaFileField(Field field) {
        List<MediaFile> mediaFiles = new ArrayList<>();
        List<String> media = Util.getImageUrls();
        media.forEach(s -> {
            MediaFile mediaFile = new MediaFile();
            mediaFile.setField(field);
            mediaFile.setFileType("image");
            mediaFile.setFileUrl(s);
            mediaFiles.add(mediaFileRepo.save(mediaFile));
        });
        return mediaFiles;
    }

    private List<MediaFile> createMediaFilePitch(Pitch pitch) {
        List<MediaFile> mediaFiles = new ArrayList<>();
        List<String> media = Util.getImageUrls();
        media.forEach(s -> {
            MediaFile mediaFile = new MediaFile();
            mediaFile.setPitch(pitch);
            mediaFile.setFileType("image");
            mediaFile.setFileUrl(s);
            mediaFiles.add(mediaFileRepo.save(mediaFile));
        });
        return mediaFiles;
    }

    private List<FiledService> createService(Field field, List<FieldServiceEnum> services) {
        List<FiledService> filedServices = new ArrayList<>();
        services.forEach(s -> {
            FiledService filedService = new FiledService();
            filedService.setField(field);
            filedService.setFiledService(s);
            filedServices.add(fieldServiceRepo.save(filedService));
        });
        return filedServices;
    }

    public List<Field> getField(String userId) {
        return fieldRepository.findByUserId(userId);
    }

    public Pitch createPitch(String userId, String fieldId, PitchCreateCommand pitchCreateCommand) {
        Optional<Field> fieldOptional = fieldRepository.findById(fieldId);
        if (fieldOptional.isPresent()) {
            Pitch pitch = new Pitch();
            pitch.setDescription(pitchCreateCommand.getDescription());
            pitch.setGrassTypeEnum(pitchCreateCommand.getGrassTypeEnum());
            pitch.setName(pitchCreateCommand.getName());
            pitch.setField(fieldOptional.get());
            pitch.setUserId(userId);
            Pitch save = pitchRepo.save(pitch);
            List<PitchTimeSlot> pitchTimeSlots = pitchCreateCommand.getPitchTimeSlots();
            if (pitchTimeSlots != null) {
                pitchTimeSlots.forEach(timeSlot -> timeSlot.setPitch(save));
                save.setPitchTimeSlots(pitchTimeSlotRepo.saveAll(pitchTimeSlots));
            }
            List<PitchTypeEnum> pitchTypes = pitchCreateCommand.getPitchTypes();
            if (pitchTypes != null) {
                List<PitchType> pitchTypeList = pitchTypes.stream().map(type -> {
                    PitchType pitchType = new PitchType();
                    pitchType.setPitchType(type);
                    pitchType.setPitch(save);
                    return pitchType;
                }).toList();
                save.setPitchTypes(pitchTypeRepo.saveAll(pitchTypeList));
            }
            save.setMediaFiles(createMediaFilePitch(save));
            return save;
        } else {
            throw new UserException("field not found");
        }
    }

    public List<Pitch> getPitch(String userId, String fieldId) {
        return pitchRepo.findByUserIdAndFieldId(userId, fieldId);
    }

    public Game createGame(String userId, GameCreateCommand gameCreateCommand) {
        Optional<Field> fieldOptional = fieldRepository.findById(gameCreateCommand.getFieldId());
        if (fieldOptional.isPresent()) {
            Optional<Pitch> pitchOptional = pitchRepo.findById(gameCreateCommand.getPitchId());
            if (pitchOptional.isPresent()) {
                Optional<PitchTimeSlot> optionalPitchTimeSlot = pitchTimeSlotRepo.findById(gameCreateCommand.getTimeSlotId());
                if (optionalPitchTimeSlot.isPresent()) {
                    Game game = new Game();
                    game.setDescription(gameCreateCommand.getDescription());
                    game.setPitch(pitchOptional.get());
                    game.setUserId(userId);
                    game.setField(fieldOptional.get());
                    game.setName(gameCreateCommand.getName());
                    game.setDate(gameCreateCommand.getDate());
                    game.setPrice(gameCreateCommand.getPrice());
                    game.setGameModeEnum(gameCreateCommand.getGameModeEnum());
                    game.setMaxOfPlayers(gameCreateCommand.getMaxOfPlayers());
                    game.setRefereeType(gameCreateCommand.getRefereeType());
                    game.setTimeSlot(optionalPitchTimeSlot.get());
                    game.setRemainingSlot(gameCreateCommand.getMaxOfPlayers());
                    game.setImageUrl(Util.getGameUrl());
                    return gameRepo.save(game);
                } else
                    throw new UserException("timeSlot not found");
            } else
                throw new UserException("pitch not found");
        } else
            throw new UserException("field not found");
    }

    public List<Game> getGames(String userId, SearchGameCommand command) {
        return customRepo.getGames(command);
    }

    public List<Game> matchGame(String userId) {
        try {
            Optional<User> userOptional = userRepo.findById(userId);
            if (userOptional.isPresent()) {
                PlayerGameInfo playerGameInfo = userOptional.get().getPlayerGameInfo();
                MatchGameCommand matchGameCommand = new MatchGameCommand();
                if (playerGameInfo != null) {
                    List<PlayerTimeTable> playerTimeTables = playerGameInfo.getPlayerTimeTables();
                    if (playerTimeTables != null && !playerTimeTables.isEmpty()) {
                        playerTimeTables.forEach(playerTimeTable -> {
                            playerTimeTable.setDateOfWeekIndex(playerTimeTable.getDateOfWeek().ordinal());
                        });
                        matchGameCommand.setPlayerTimeTables(playerGameInfo.getPlayerTimeTables());
                    }
                    List<Location> preferLocations = playerGameInfo.getPreferLocations();
                    if (preferLocations != null && !preferLocations.isEmpty()) {
                        matchGameCommand.setPreferLocations(preferLocations.get(0));
                    }
                    matchGameCommand.setPreferPriceEnd(playerGameInfo.getPreferPriceEnd());
                    matchGameCommand.setPreferPriceStart(playerGameInfo.getPreferPriceStart());
                }
                return customRepo.matchGame(new MatchGameCommand());
            } else {
                throw new UserException("user not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Boolean joinGame(String userId, String gameId) {
        Optional<User> userOptional = userRepo.findById(userId);
        if (userOptional.isPresent()) {
            Optional<Game> gameOptional = gameRepo.findById(gameId);
            if (gameOptional.isPresent()) {
                Optional<GameRegisteredUser> optional = gameRegisterRepo.findByUserIdAndGameId(userId, gameId);
                if (optional.isEmpty()) {
                    Game game = gameOptional.get();
                    GameRegisteredUser gameRegisteredUser = new GameRegisteredUser();
                    gameRegisteredUser.setGame(game);
                    gameRegisteredUser.setUser(userOptional.get());
                    if (game.getRemainingSlot() > 0) {
                        game.setRemainingSlot(game.getRemainingSlot() - 1);
                        gameRegisterRepo.save(gameRegisteredUser);
                        return true;
                    } else {
                        throw new UserException("Game slot is full");
                    }
                } else {
                    throw new UserException("You already joined the game");
                }

            }
        }
        throw new UserException("user not found");
    }

    public List<Game> myGames(String userId) {
        return gameRepo.findMyGame(userId);
    }
}
