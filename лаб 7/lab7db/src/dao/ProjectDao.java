package dao;

import domain.Project;

import java.util.List;

import domain.Lab4Task22;

public interface ProjectDao extends Dao<Project>{
	List<Lab4Task22> readUnfinishedProjectsClient(Long clientId) throws DaoException;
}
