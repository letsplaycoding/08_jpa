package com.ohgiraffers.springdatajpa.menu.repository;

import com.ohgiraffers.springdatajpa.menu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Integer> {
                                        // <entity, pk(@Id)를 준 필드의 자료형(기본 자료형으로 표기x, ex.int -> Integer)
    List<Menu> findByMenuPriceGreaterThan(int menuPrice);
}
