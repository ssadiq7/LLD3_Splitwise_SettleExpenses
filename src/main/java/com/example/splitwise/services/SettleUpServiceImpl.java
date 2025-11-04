package com.example.splitwise.services;

import com.example.splitwise.exceptions.InvalidGroupException;
import com.example.splitwise.exceptions.InvalidUserException;
import com.example.splitwise.models.Transaction;
import com.example.splitwise.repositories.GroupRepository;
import com.example.splitwise.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SettleUpServiceImpl implements SettleUpService {

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    @Autowired
    public SettleUpServiceImpl(UserRepository userRepository, GroupRepository groupRepository) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
    }

    @Override
    public List<Transaction> settleGroup(long groupId) throws InvalidGroupException {
        return List.of();
    }

    @Override
    public List<Transaction> settleUser(long userId) throws InvalidUserException {
        return List.of();
    }

}
