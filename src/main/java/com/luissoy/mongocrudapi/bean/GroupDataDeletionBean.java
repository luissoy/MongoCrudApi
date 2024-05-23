package com.luissoy.mongocrudapi.bean;

import com.luissoy.mongocrudapi.dto.GroupDto;
import com.luissoy.mongocrudapi.exception.DataIntegrityException;
import com.luissoy.mongocrudapi.exception.DataNotFoundException;
import com.luissoy.mongocrudapi.model.Group;
import com.luissoy.mongocrudapi.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GroupDataDeletionBean {

    @Autowired
    private GroupService groupService;

    public void deleteUserFromGroupsByUserId(String userId) throws DataNotFoundException, DataIntegrityException {
        List<Group> groups = groupService.getByUserId(userId);
        for (Group group : groups) {
            group.getUserIds().remove(userId);
            groupService.update(group.getId(), new GroupDto(group));
        }
    }

}