package me.savvy.main.dao;

import me.savvy.api.dao.ApexDao;
import me.savvy.api.players.ApexPlayer;

import java.util.Optional;
import java.util.UUID;

public class ApexMongoDao implements ApexDao {

    @Override
    public void insert(ApexPlayer apexPlayer) {
        
    }

    @Override
    public void save(ApexPlayer apexPlayer) {

    }

    @Override
    public void saveSync(ApexPlayer apexPlayer) {

    }

    @Override
    public void delete(ApexPlayer apexPlayer) {

    }

    @Override
    public void delete(UUID uuid) {

    }

    @Override
    public Optional<ApexPlayer> findByUniqueId(UUID uniqueId) {
        return Optional.empty();
    }
}
