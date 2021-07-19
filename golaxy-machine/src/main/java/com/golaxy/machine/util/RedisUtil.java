package com.golaxy.machine.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author miaoxuebing
 * @Description: redsi工具类
 * @date 2021/1/25 下午12:53
 */
@Component
public class RedisUtil {

    @Autowired
    private static RedisTemplate<String, Object> redisTemplate;

    /**
     * @Description: 指定缓存失效时间
     * @Params: [key, time]
     * @Return: boolean
     * @Author: miaoxuebing
     * @Date: 2021/1/25 下午12:55
     **/
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @Description: 根据key获取失效时间
     * @Params: [key]
     * @Return: long
     * @Author: miaoxuebing
     * @Date: 2021/1/25 下午12:56
     **/
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }


    /**
     * @Description: 判断key是否存在
     * @Params: [key]
     * @Return: boolean
     * @Author: miaoxuebing
     * @Date: 2021/1/25 下午12:59
     **/
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @Description: 删除缓存
     * @Params: [key]
     * @Return: void
     * @Author: miaoxuebing
     * @Date: 2021/1/25 下午1:02
     **/
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete((Collection<String>) CollectionUtils.arrayToList(key));
            }
        }
    }

    /**
     * @Description: 普通缓存获取
     * @Params: [key]
     * @Return: java.lang.Object
     * @Author: miaoxuebing
     * @Date: 2021/1/25 下午1:56
     **/
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * @Description: 普通缓存放入
     * @Params: [key, value]
     * @Return: boolean
     * @Author: miaoxuebing
     * @Date: 2021/1/25 下午1:57
     **/
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @Description: 普通缓存放入并设置时间
     * @Params: [key, value, time]
     * @Return: boolean
     * @Author: miaoxuebing
     * @Date: 2021/1/25 下午1:58
     **/
    public boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @Description: 递增
     * @Params: [key, delta]
     * @Return: long
     * @Author: miaoxuebing
     * @Date: 2021/1/25 下午2:04
     **/
    public long incr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
    * @Description: 递减
    * @Params: [key, delta]
    * @Return: long
    * @Author: miaoxuebing
    * @Date: 2021/1/25 下午2:05
    **/
    public long decr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, -delta);
    }

    /**
     * @Description: HashGet
     * @Params: [key, item]
     * @Return: java.lang.Object
     * @Author: miaoxuebing
     * @Date: 2021/1/25 下午2:07
     **/
    public Object hget(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * @Description: 获取hashKey对应的所有键值
     * @Params: [key]
     * @Return: java.util.Map<java.lang.Object, java.lang.Object>
     * @Author: miaoxuebing
     * @Date: 2021/1/25 下午2:08
     **/
    public Map<Object, Object> hmget(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
    * @Description: HashSet
    * @Params: [key, map]
    * @Return: boolean
    * @Author: miaoxuebing
    * @Date: 2021/1/25 下午2:09
    **/
    public boolean hmset(String key, Map<String, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @Description: HashSet 并设置时间
     * @Params: [key, map, time]
     * @Return: boolean
     * @Author: miaoxuebing
     * @Date: 2021/1/25 下午2:10
     **/
    public boolean hmset(String key, Map<String, Object> map, long time) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * @Description: 向一张hash表中放入数据, 如果不存在将创建
     * @Params: [key, item, value]
     * @Return: boolean
     * @Author: miaoxuebing
     * @Date: 2021/1/25 下午2:12
     **/
    public boolean hset(String key, String item, Object value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @Description: 向一张hash表中放入数据, 如果不存在将创建 注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @Params: [key, item, value, time]
     * @Return: boolean
     * @Author: miaoxuebing
     * @Date: 2021/1/25 下午2:13
     **/
    public boolean hset(String key, String item, Object value, long time) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @Description: 删除hash表中的值
     * @Params: [key, item]
     * @Return: void
     * @Author: miaoxuebing
     * @Date: 2021/1/25 下午2:14
     **/
    public void hdel(String key, Object... item) {
        redisTemplate.opsForHash().delete(key, item);
    }

    
    /**
    * @Description: 判断hash表中是否有该项的值
    * @Params: [key, item]
    * @Return: boolean
    * @Author: miaoxuebing
    * @Date: 2021/1/25 下午2:15
    **/
    public boolean hHasKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    /**
    * @Description: hash递增 如果不存在,就会创建一个 并把新增后的值返回
    * @Params: [key, item, by]
    * @Return: double
    * @Author: miaoxuebing
    * @Date: 2021/1/25 下午2:16
    **/
    public double hincr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, by);
    }

    /**
     * @Description: hash递减
     * @Params: [key, item, by]
     * @Return: double
     * @Author: miaoxuebing
     * @Date: 2021/1/25 下午2:16
     **/
    public double hdecr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, -by);
    }

    /**
     * @Description: 根据key获取Set中的所有值
     * @Params: [key]
     * @Return: Set<Object>
     * @Author: miaoxuebing
     * @Date: 2021/1/25 下午2:17
     **/
    public Set<Object> sGet(String key) {
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @Description: 根据value从一个set中查询, 是否存在
     * @Params: [key, value]
     * @Return: boolean
     * @Author: miaoxuebing
     * @Date: 2021/1/25 下午2:17
     **/
    public boolean sHasKey(String key, Object value) {
        try {
            return redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @Description: 将数据放入set缓存
     * @Params: [key, values]
     * @Return: long
     * @Author: miaoxuebing
     * @Date: 2021/1/25 下午2:18
     **/
    public long sSet(String key, Object... values) {
        try {
            return redisTemplate.opsForSet().add(key, values);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * @Description: 将数据放入set缓存, 设置时间
     * @Params: [key, time, values]
     * @Return: long
     * @Author: miaoxuebing
     * @Date: 2021/1/25 下午2:19
     **/
    public long sSetAndTime(String key, long time, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().add(key, values);
            if (time > 0) {
                expire(key, time);
            }
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * @Description: 获取set缓存的长度
     * @Params: [key]
     * @Return: long
     * @Author: miaoxuebing
     * @Date: 2021/1/25 下午2:20
     **/
    public long sGetSetSize(String key) {
        try {
            return redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * @Description: 移除值为value的
     * @Params: [key, values]
     * @Return: long
     * @Author: miaoxuebing
     * @Date: 2021/1/25 下午2:21
     **/
    public long setRemove(String key, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().remove(key, values);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * @Description: 获取list缓存的内容
     * @Params: [key, start, end]
     * @Return: java.util.List<java.lang.Object>
     * @Author: miaoxuebing
     * @Date: 2021/1/25 下午2:22
     **/
    public List<Object> lGet(String key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @Description: 获取list缓存的长度
     * @Params: [key]
     * @Return: long
     * @Author: miaoxuebing
     * @Date: 2021/1/25 下午2:22
     **/
    public long lGetListSize(String key) {
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * @Description: 通过索引 获取list中的值 索引 index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * @Params: [key, index]
     * @Return: java.lang.Object
     * @Author: miaoxuebing
     * @Date: 2021/1/25 下午2:23
     **/
    public Object lGetIndex(String key, long index) {
        try {
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @Description: 将list放入缓存
     * @Params: [key, value]
     * @Return: boolean
     * @Author: miaoxuebing
     * @Date: 2021/1/25 下午2:24
     **/
    public boolean lSet(String key, Object value) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
    * @Description: 将list放入缓存,同时设置时间
    * @Params: [key, value, time]
    * @Return: boolean
    * @Author: miaoxuebing
    * @Date: 2021/1/25 下午2:25
    **/
    public boolean lSet(String key, Object value, long time) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @Description: 将list放入缓存
     * @Params: [key, value]
     * @Return: boolean
     * @Author: miaoxuebing
     * @Date: 2021/1/25 下午2:27
     **/
    public boolean lSet(String key, List<Object> value) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @Description: 将list放入缓存，设置时间
     * @Params: [key, value, time]
     * @Return: boolean
     * @Author: miaoxuebing
     * @Date: 2021/1/25 下午2:27
     **/
    public boolean lSet(String key, List<Object> value, long time) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @Description: 根据索引修改list中的某条数据
     * @Params: [key, index, value]
     * @Return: boolean
     * @Author: miaoxuebing
     * @Date: 2021/1/25 下午2:29
     **/
    public boolean lUpdateIndex(String key, long index, Object value) {
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
