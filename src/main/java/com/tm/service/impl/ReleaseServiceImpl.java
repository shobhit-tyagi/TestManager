package com.tm.service.impl;

import com.tm.dao.entity.TmRelease;
import com.tm.dao.db.ReleaseDao;
import com.tm.dao.db.UserDao;
import com.tm.model.ui.ReleaseBean;
import com.tm.service.ReleaseService;
import com.tm.model.assembler.DtoAssemblerFacadeImpl;
import com.tm.util.exceptions.DtoConversionException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ReleaseServiceImpl extends DtoAssemblerFacadeImpl<TmRelease, ReleaseBean> implements ReleaseService {

	private final ReleaseDao releaseDao;
	private final UserDao userDao;

	@Override
	public List<ReleaseBean> getReleasesByModule(final long moduleId) throws DtoConversionException {
		final List<TmRelease> releaseEntityList = releaseDao.findByModId(moduleId);
		final List<ReleaseBean> releaseBeanList = new ArrayList<ReleaseBean>();
		for(final TmRelease releaseEntity : releaseEntityList) {
			final ReleaseBean releaseBean = toBean(releaseEntity);
			releaseBean.setUserIdString(userDao.findById(releaseBean.getUserId()).get().getUserId());
			releaseBeanList.add(releaseBean);
		}
		return releaseBeanList;
	}

	@Override
	public ReleaseBean addReleaseToModule(ReleaseBean releaseBean) throws DtoConversionException {
		TmRelease releaseEntity = toEntity(releaseBean);
		releaseEntity = releaseDao.save(releaseEntity);
		releaseBean = toBean(releaseEntity);
		releaseBean.setUserIdString(userDao.findById(releaseBean.getUserId()).get().getUserId());
		return releaseBean;
	}
}
