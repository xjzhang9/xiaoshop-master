package com.xjzhang.sys.user.convert;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.ArrayList;
import com.xjzhang.sys.user.model.UserDto;
import com.xjzhang.sys.user.model.UserVo;
import com.xjzhang.sys.user.model.User;

/**
 * 系统用户 bean 转换
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-09-19 20:45:50
 */

public class UserConvert {

    /**
     * entity -> vo
     *
     * @return
     */
    public static UserVo entity2Vo(User user) {
        if (user == null) {
            return null;
        }
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);

        return userVo;
    }

    /**
     * dto -> entity
     *
     * @return
     */
    public static User dto2Entity(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        User user = new User();
        BeanUtils.copyProperties(userDto, user);

        return user;
    }


    /**
     * entityList -> VoList
     *
     * @return
     */
    public static List<UserVo> entity2VoList(List<User> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        List<UserVo> listVo = new ArrayList<UserVo>();
        for (User item :
                list) {
            listVo.add(entity2Vo(item));
        }
        return listVo;
    }


    /**
     * dtoList -> EntityList
     *
     * @return
     */
    public static List<User> dto2EntityList(List<UserDto> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        List<User> listDo = new ArrayList<User>();
        for (UserDto dto : list) {
            listDo.add(dto2Entity(dto));
        }
        return listDo;
    }

    /**
     * entityPage -> VoPage
     *
     * @return
     */
    public static IPage<UserVo> entity2VoPage(IPage<User> page) {
        IPage<UserVo> vs = page.convert(item -> {
            try {
                return entity2Vo(item);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        });
        return vs;
    }
}
