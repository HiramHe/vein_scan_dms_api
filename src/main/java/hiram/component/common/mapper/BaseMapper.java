package hiram.component.common.mapper;

import java.util.List;

/**
 * @Author: HiramHe
 * @Date: 2020/4/23 20:37
 * @Description: "Entity与DTO互相转换"
 */

public interface BaseMapper<D,E> {

    E toEntity(D dto);

    D toDto(E entity);

    List<E> toEntityList(List<D> dtoList);

    List<D> toDtoList(List<E> entityList);
}
