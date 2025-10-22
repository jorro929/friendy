package ru.grinin.friendy.back.mapper;

public interface Mapper <F, T>{
    T mapTo(F object);
}
