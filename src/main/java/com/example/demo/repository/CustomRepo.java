package com.example.demo.repository;

import com.example.demo.controller.request.MatchGameCommand;
import com.example.demo.controller.request.SearchGameCommand;
import com.example.demo.entity.Game;
import com.example.demo.entity.PlayerTimeTable;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomRepo {
    private final EntityManager em;

    public CustomRepo(EntityManager em) {
        this.em = em;
    }

    public List<Game> getGames(SearchGameCommand command) {
        String query = searchGameSql(command);
        Query nativeQuery = em.createNativeQuery(query, Game.class);
        return nativeQuery.getResultList();
    }

    public String searchGameSql(SearchGameCommand command) {
        String query = "SELECT game.*\n" +
                "from game\n" +
                "         join field on field.id = game.fieldId\n" +
                "         join pitch on game.pitchId = pitch.id\n" +
                "         join pitchtimeslot on game.timeSlotId = pitchtimeslot.id\n" +
                "where 1 = 1\n";
        if (command.getDate() != null) {
            query += " and game.date = '" + command.getDate() + "'";
        }
        if (command.getCity() != null) {
            query += " and field.city = '" + command.getCity() + "'";
        }
        if (command.getWard() != null) {
            query += " and field.ward = '" + command.getWard() + "'";
        }
        if (command.getDistrict() != null) {
            query += " and field.district = '" + command.getDistrict() + "'";
        }
        if (command.getStartHour() != null) {
            query += " and pitchtimeslot.startHour >= '" + command.getStartHour() + "'";
        }
        if (command.getEndHour() != null) {
            query += " and pitchtimeslot.endHour >= '" + command.getEndHour() + "'";
        }
        if (command.getPitchTypeEnum() != null) {
            query += " and pitch.pitchType = '" + command.getPitchTypeEnum() + "'";
        }
        if (command.getPreferPriceStart() != null) {
            query += " and game.price >= '" + command.getPreferPriceStart() + "'";
        }
        if (command.getPreferPriceEnd() != null) {
            query += " and game.price <= '" + command.getPreferPriceEnd() + "'";
        }
        query += ";";
        return query;
    }

    public String matchGameSql(MatchGameCommand command) {
        String query = "SELECT game.*\n" +
                "from game\n" +
                "         join field on field.id = game.fieldId\n" +
                "         join pitch on game.pitchId = pitch.id\n" +
                "         join pitchtimeslot on game.timeSlotId = pitchtimeslot.id\n" +
                "where game.remainingSlot > 0 \n";
        if (command.getPreferLocations() != null) {
            query += "  and field.city = : city\n" +
                    "  and field.district = :district\n";
        }
        if (command.getPreferPriceStart() != null) {
            query += " and game.price >= '" + command.getPreferPriceStart() + "'";
        }
        if (command.getPreferPriceEnd() != null) {
            query += " and game.price <= '" + command.getPreferPriceEnd() + "'";
        }
        List<PlayerTimeTable> playerTimeTables = command.getPlayerTimeTables();
        if (playerTimeTables != null && !playerTimeTables.isEmpty()) {
            query += "and (";
            for (int i = 0; i < playerTimeTables.size(); i++) {
                PlayerTimeTable playerTimeTable = playerTimeTables.get(i);
                if (i == 0) {
                    query += String.format("(DAYOFWEEK(game.date) = %s and pitchtimeslot.startHour >= %s and pitchtimeslot.startHour <= %s)",
                            playerTimeTable.getDateOfWeekIndex(), playerTimeTable.getStartHour(), playerTimeTable.getEndHour());
                } else {
                    query += String.format("or (DAYOFWEEK(game.date) = %s and pitchtimeslot.startHour >= %s and pitchtimeslot.startHour <= %s)",
                            playerTimeTable.getDateOfWeekIndex(), playerTimeTable.getStartHour(), playerTimeTable.getEndHour());
                }
            }
            query += ")";
        }
        query += "limit 20;";
        return query;
    }
    public List<Game> matchGame(MatchGameCommand command) {
        String query = matchGameSql(command);
        Query nativeQuery = em.createNativeQuery(query, Game.class);
        return nativeQuery.getResultList();
    }

}
