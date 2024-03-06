package com.comparusua.dataaggregator.database.db2.repository;

import com.comparusua.dataaggregator.database.db2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface DB2UserRepository extends JpaRepository<User, UUID>, JpaSpecificationExecutor<User> {
}
