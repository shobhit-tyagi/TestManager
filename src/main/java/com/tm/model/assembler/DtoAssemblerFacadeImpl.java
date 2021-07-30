package com.tm.model.assembler;

import com.inspiresoftware.lib.dto.geda.assembler.DTOAssembler;
import com.tm.util.exceptions.DtoConversionException;

import java.lang.reflect.ParameterizedType;

public class DtoAssemblerFacadeImpl<E, B> implements DtoAssemblerFacade<E, B> {

	@Override
	public B toBean(final E entity) throws DtoConversionException {
		final Class<B> classB = getBeanClass();
		final B bean;
		try {
			bean = classB.newInstance();
		} catch (final InstantiationException | IllegalAccessException e) {
			throw new DtoConversionException("Conversion failure", e);
		}
		DTOAssembler.newAssembler(classB, getEntityClass()).assembleDto(bean, entity, null, null);
		return bean;
	}

	@Override
	public E toEntity(final B bean) throws DtoConversionException {
		final Class<E> classE = getEntityClass();
		final E entity;
		try {
			entity = classE.newInstance();
		} catch (final InstantiationException | IllegalAccessException e) {
			throw new DtoConversionException("Conversion failure", e);
		}
		DTOAssembler.newAssembler(getBeanClass(), classE).assembleEntity(bean, entity, null, null);
		return entity;
	}
	
	@SuppressWarnings("unchecked")
	private Class<E> getEntityClass() {
		 final ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
	     return (Class<E>) genericSuperclass.getActualTypeArguments()[0];
	}
	
	@SuppressWarnings("unchecked")
	private Class<B> getBeanClass() {
		 final ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
	     return (Class<B>) genericSuperclass.getActualTypeArguments()[1];
	}
}
