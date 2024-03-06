package com.comparusua.dataaggregator.service;

import com.comparusua.dataaggregator.api.dto.UserDto;
import com.comparusua.dataaggregator.api.dto.UserFilterDto;
import com.comparusua.dataaggregator.database.db1.repository.DB1UserRepository;
import com.comparusua.dataaggregator.database.db1.specification.UserSpecificationDB1;
import com.comparusua.dataaggregator.database.db2.repository.DB2UserRepository;
import com.comparusua.dataaggregator.database.db2.specification.UserSpecificationDB2;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class UserService {

    private final DB1UserRepository db1UserRepository;
    private final DB2UserRepository db2UserRepository;
    private final ModelMapper modelMapper;

    public List<UserDto> getAllUsers(Pageable pageable, UserFilterDto userFilterDto){
        Stream<UserDto> db1Users = getUsersFromDB1(pageable, userFilterDto);
        Stream<UserDto> db2Users = getUsersFromDB2(pageable, userFilterDto);

        return Stream.concat(db1Users, db2Users).collect(Collectors.toList());
    }

    private Stream<UserDto> getUsersFromDB1(Pageable pageable, UserFilterDto userFilterDto) {
        UserSpecificationDB1 userSpecDb1 = new UserSpecificationDB1(userFilterDto);
        return db1UserRepository.findAll(userSpecDb1, pageable).stream()
                .map(user -> modelMapper.map(user, UserDto.class));
    }

    private Stream<UserDto> getUsersFromDB2(Pageable pageable, UserFilterDto userFilterDto) {
        UserSpecificationDB2 userSpecDb2 = new UserSpecificationDB2(userFilterDto);
        return db2UserRepository.findAll(userSpecDb2, pageable).stream()
                .map(user -> modelMapper.map(user, UserDto.class));
    }
}
