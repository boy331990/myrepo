package first.sample.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import first.common.common.CommandMap;
import first.sample.service.SampleService;

/*
 * 	@Controller Annotation은 SampleController.java가 컨트롤러라는것을 명시함 
 * */
@Controller
public class SampleController {
	Logger log = Logger.getLogger(this.getClass());

	/*
	 * @RequestMapping Annotation으로DispatcherServlet에서
	 * 이Annotation을기준으로어떤컨트롤러의메서드가호출되어야할지를결정
	 * (value="/sample/openSampleList.do")는프로젝트가실행될주소
	 
	@RequestMapping(value = "/sample/openSampleList.do")
	
	 * ModelAndView 클래스 형식의 mv 인스턴스를 생성 new ModelAndView("")를 할때 생성자 부분에서 이
	 * 컨트롤러가 실행되고 나서 보여줄 view(사용자에게 보여줄 화면)를 설정 할 수 있다. 
	 * 여기서는 인터넵터가 동작하는지 확인하기 위한 테스트이므로 따로 view를 설정하지 않는다.
	 
	public ModelAndView openSampleList(Map<String, Object> commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("");
		log.debug("인터셉터 테스트");// 컨트롤러가 실행되고 log4j의 로거의 동작을 보려는 것

		return mv;
	}*/
	
	@Resource(name="sampleService")
    private SampleService sampleService;
     
    @RequestMapping(value="/sample/openBoardList.do")
    public ModelAndView openSampleBoardList(Map<String,Object> commandMap) throws Exception{
        ModelAndView mv = new ModelAndView("/sample/boardList");
         
        List<Map<String,Object>> list = sampleService.selectBoardList(commandMap);
        mv.addObject("list", list);
         
        return mv;
    }
	
	@RequestMapping(value="/sample/testMapArgumentResolver.do")
	public ModelAndView testMapArgumentResolver(CommandMap commandMap) throws Exception{
	    ModelAndView mv = new ModelAndView("");
	     
	    if(commandMap.isEmpty() == false){
	        Iterator<Entry<String,Object>> iterator = commandMap.getMap().entrySet().iterator();
	        Entry<String,Object> entry = null;
	        while(iterator.hasNext()){
	            entry = iterator.next();
	            log.debug("key : "+entry.getKey()+", value : "+entry.getValue());
	        }
	    }
	    return mv;
	}
	
	
	@RequestMapping(value="/sample/openBoardWrite.do")
	public ModelAndView openBoardWrite(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/sample/boardWrite");
		
		return mv;
	}
	
	@RequestMapping(value="/sample/insertBoard.do")
	public ModelAndView insertBoard(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/sample/openBoardList.do");
		
		sampleService.insertBoard(commandMap.getMap());
		
		return mv;
	}
	
	@RequestMapping(value="/sample/openBoardDetail.do")
	public ModelAndView openBoardDetail(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/sample/boardDetail");
		
		Map<String, Object> map = sampleService.selectBoardDetail(commandMap.getMap());
		mv.addObject("map", map);
		
		return mv;
	}
}
