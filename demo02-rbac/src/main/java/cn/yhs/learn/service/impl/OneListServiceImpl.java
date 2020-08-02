package cn.yhs.learn.service.impl;

import cn.yhs.learn.dao.OneListDao;
import cn.yhs.learn.domain.OneList;
import cn.yhs.learn.service.OneListService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: ssm-project-demo
 * @Name: cn.yhs.learn.service.impl.OneListServiceImpl
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/7/18 14:56
 * @Description: todo
 **/
@Service
@Slf4j
public class OneListServiceImpl implements OneListService {
    @Autowired
    private OneListDao oneListDao;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 使用了redis做缓存
     *
     * @return
     */
    @Override
    public List<OneList> findAll() {
        // 用户对象和json串之间的转换
        ObjectMapper objectMapper = new ObjectMapper();
        // 排除json字符串中实体类没有的字段
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 从redis获取操作字符串类型的对象
        ValueOperations<String, String> stringValues = redisTemplate.opsForValue();
        // 从redis中获取缓存的数据
        String oneListsJson = stringValues.get("oneLists");
        if (null == oneListsJson) { // redis中没有数据
            List<OneList> oneListList = oneListDao.findAllWithTwoList();
            String jsonValue = null;
            try {
                jsonValue = objectMapper.writeValueAsString(oneListList); // 将从mysql中查询的数据转换成json字符串，保存到redis中
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            // 保存到redis中
            stringValues.set("oneLists", jsonValue);
            log.info("redis中没有列表数据，从数据库中进行查询，然后放到redis中");
            return oneListList;
        }
        //redis中有缓冲数据， 将数据转换成我们想要的数据类型
        JavaType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, OneList.class);
        List<OneList> oneLists = null;
        try {
            oneLists = objectMapper.readValue(oneListsJson, collectionType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        //  log.info("redis中有列表数据，从redis数据库中进行查询");
        return oneLists;

    }
}
