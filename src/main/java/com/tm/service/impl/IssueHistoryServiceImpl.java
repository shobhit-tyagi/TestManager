package com.tm.service.impl;

import com.tm.dao.entity.TmIssueHistory;
import com.tm.dao.db.IssueHistoryDao;
import com.tm.model.ui.IssueHistoryBean;
import com.tm.service.IssueHistoryService;
import com.tm.model.assembler.DtoAssemblerFacadeImpl;
import com.tm.util.exceptions.DtoConversionException;
import com.tm.util.exceptions.FileLoadException;
import com.tm.util.file.PropertyUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Service
@RequiredArgsConstructor
public class IssueHistoryServiceImpl extends DtoAssemblerFacadeImpl<TmIssueHistory, IssueHistoryBean> implements IssueHistoryService {

	@NonNull
	private final IssueHistoryDao issueHistoryDao;
	static Properties historyProp;
	
	static {
		try {
			historyProp = PropertyUtils.loadProperties("issue/history/activity_en.properties");
		} catch (final FileLoadException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<IssueHistoryBean> getIssueHistory(final long issueId) throws DtoConversionException {
		final List<TmIssueHistory> issueHistoryEntityList = issueHistoryDao.findByIssId(issueId);
		final List<IssueHistoryBean> issueHistoryBeanList = new ArrayList<IssueHistoryBean>();
		for(final TmIssueHistory issueHistoryEntity : issueHistoryEntityList) {
			final IssueHistoryBean issueHistoryBean = toBean(issueHistoryEntity);
			issueHistoryBean.setHisContent(historyProp.getProperty(issueHistoryBean.getHisContent(), DEFAULT_MESSG));
			issueHistoryBeanList.add(issueHistoryBean);
		}
		return issueHistoryBeanList;
	}
	
	@Override
	public Properties getHistoryProperties() {
		return historyProp;
	}
}
