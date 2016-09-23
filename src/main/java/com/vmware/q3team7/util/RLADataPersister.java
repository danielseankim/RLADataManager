package com.vmware.q3team7.util;

import java.util.HashMap;
import java.util.Map;

import com.vmware.q3team7.exceptions.RLADataAlreadyExists;
import com.vmware.q3team7.exceptions.RLADataNotFoundException;
import com.vmware.q3team7.models.RLAData;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * @author kdaniel
 *
 */
public class RLADataPersister {
    private static Jedis jedis;
    private static RLADataPersister instance;
    
    protected RLADataPersister() {}
    
    public static RLADataPersister getJedisPersister() throws JedisConnectionException{
        if (instance == null) {
            instance = new RLADataPersister();
            Jedis njedis = new Jedis("localhost");
            instance.setJedis(njedis);
        }
        return instance;
    }

    public Jedis getJedis() {
        return jedis;
    }

    private void setJedis(Jedis njedis) {
        this.jedis = njedis;
    }

    /**
     * This method is used to create or update the RLAData in redis storage
     * @throws RLADataAlreadyExists 
     */
    public void storeRLAData(RLAData data) throws RLADataAlreadyExists {
        if (jedis.exists(data.getId())) {
            throw new RLADataAlreadyExists("Cannot store data with id, " + data.getId() + ", since it already exists.");
        }
        
        Map<String, String> map = new HashMap<>();
        map.put(RLAConstants.RLA_DATA_NAME, data.getName());
        map.put(RLAConstants.RLA_DATA_ID, data.getId());
        map.put(RLAConstants.RLA_DATA_TAG, data.getTag());
        map.put(RLAConstants.RLA_DATA_VERSION, data.getVersion());
        if (data.getContent() != null && !data.getContent().isEmpty()) {
            map.put(RLAConstants.RLA_DATA_CONTENT, data.getContent());
        }
        jedis.hmset(data.getId(), map);
    }

    /**
     * This method is used to fetch the RLAData from redis storage
     * @throws RLADataNotFoundException 
     */
    public RLAData getRLAData(String dataId) throws RLADataNotFoundException {
        Map<String, String> map = jedis.hgetAll(dataId);
        if (map == null || map.isEmpty()) {
            throw new RLADataNotFoundException(dataId);
        }
        RLAData result = new RLAData();
        result.setId(dataId);
        if (map.get(RLAConstants.RLA_DATA_CONTENT) != null) {
            result.setContent(map.get(RLAConstants.RLA_DATA_CONTENT));
        }
        result.setName(map.get(RLAConstants.RLA_DATA_NAME));
        result.setTag(map.get(RLAConstants.RLA_DATA_TAG));
        result.setVersion(map.get(RLAConstants.RLA_DATA_VERSION));
        return result;
    }

    /**
     * This method is used to remove the RLAData from redis storage
     * @throws RLADataNotFoundException 
     */
    public String removeRLAData(String dataId) throws RLADataNotFoundException {
        Long isDeleted = jedis.del(dataId);
        if (isDeleted != 1) {
            throw new RLADataNotFoundException(dataId);
        }
        return dataId;
    }
}
