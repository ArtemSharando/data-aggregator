package com.comparusua.dataaggregator.database.db1.repository;

import com.comparusua.dataaggregator.database.db1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface DB1UserRepository extends JpaRepository<User, UUID>, JpaSpecificationExecutor<User> {
}
