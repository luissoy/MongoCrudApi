package com.luissoy.mongocrudapi.dto;

import com.luissoy.mongocrudapi.model.Group;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupDto {

    private String name;

    private List<String> userIds;

    public GroupDto(Group group) {
        this.name = group.getName();
        this.userIds = group.getUserIds();
    }
}