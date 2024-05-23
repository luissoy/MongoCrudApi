package com.luissoy.mongocrudapi.service;

import com.luissoy.mongocrudapi.bean.CustomPropertiesBean;
import com.luissoy.mongocrudapi.dto.GroupDto;
import com.luissoy.mongocrudapi.exception.DataIntegrityException;
import com.luissoy.mongocrudapi.exception.DataNotFoundException;
import com.luissoy.mongocrudapi.model.Group;
import com.luissoy.mongocrudapi.repository.GroupRepository;
import com.luissoy.mongocrudapi.response.PageResponse;
import com.luissoy.mongocrudapi.util.ServiceExceptionsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public PageResponse<Group> getAll(Pageable pageable) {
        return new PageResponse<>(groupRepository.findAll(pageable));
    }

    public Group getOne(String id) throws DataNotFoundException {
        return ServiceExceptionsUtil.
                getObjectOrDataNotFound(
                        groupRepository.findById(id),
                        CustomPropertiesBean.getProperty("exception.data.not-found.group")
                );
    }

    public List<Group> getByUserId(String userId) {
        return groupRepository.findGroupsByUserId(userId);
    }

    public Group save(GroupDto dto) throws DataIntegrityException {
        validateDtoDataIntegrity(dto);

        return groupRepository.save(new Group(dto));
    }

    public Group update(String id, GroupDto dto) throws DataNotFoundException, DataIntegrityException {
        Group old = getOne(id);

        validateDtoDataIntegrity(dto);

        Group group = new Group(
                id,
                dto
        );

        return groupRepository.save(group);
    }

    public void delete(String id) throws DataNotFoundException {
        ServiceExceptionsUtil.existsOrDataNotFound(
                groupRepository.existsById(id),
                CustomPropertiesBean.getProperty("exception.data.not-found.group")
        );

        groupRepository.deleteById(id);
    }

    private void validateDtoDataIntegrity(GroupDto dto) throws DataIntegrityException {
        ServiceExceptionsUtil.notEmptyOrDataIntegrity(
                dto.getName(),
                CustomPropertiesBean.getProperty("exception.data.integrity.group.name.empty")
        );

        ServiceExceptionsUtil.notEmptyOrDataIntegrity(
                dto.getUserIds(),
                CustomPropertiesBean.getProperty("exception.data.integrity.group.user-ids.empty")
        );
    }

}