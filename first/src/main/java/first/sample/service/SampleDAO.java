package first.sample.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import first.common.dao.AbstractDAO;

@Repository("sampleDAO")
public class SampleDAO extends AbstractDAO {
	
	public List<Map<String, Object>> selectBoardList(Map<String, Object> map) {
		
		return (List<Map<String,Object>>)selectList("sample.selectBoardList", map);
		// "sample.selectBoardList"가 쿼리 이름이고, map이 Controller에서부터 계속 넘어온 쿼리 실행시 필요 변수이다.
		// "sample.selectBoardList"의 sample이라는 부분이 바로 xml에서 설정한 namespace의 이름이다.
		
	}

	public void insertBoard(Map<String, Object> map) throws Exception {
		insert("sample.insertBoard", map);
	}

	public void updateHitCnt(Map<String, Object> map) throws Exception {
		update("sample.updateHitCnt", map);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> selectBoardDetail(Map<String, Object> map) throws Exception {
		return (Map<String, Object>) selectOne("sample.selectBoardDetail", map);
	}
}