package com.tm.service;

import com.tm.dao.entity.TmRelease;
import com.tm.model.assembler.DtoAssemblerFacade;
import com.tm.model.ui.ReleaseBean;
import com.tm.util.exceptions.DtoConversionException;

import java.util.List;

public interface ReleaseService extends DtoAssemblerFacade<TmRelease, ReleaseBean> {

	List<ReleaseBean> getReleasesByModule(long moduleId) throws DtoConversionException;

	ReleaseBean addReleaseToModule(ReleaseBean releaseBean) throws DtoConversionException;
}
