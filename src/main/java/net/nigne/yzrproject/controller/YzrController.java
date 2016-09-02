package net.nigne.yzrproject.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.nigne.yzrproject.domain.Criteria;
import net.nigne.yzrproject.domain.MemberVO;
import net.nigne.yzrproject.domain.MovieVO;
import net.nigne.yzrproject.domain.NoticeVO;
import net.nigne.yzrproject.domain.ReservationVO;
import net.nigne.yzrproject.service.ActorService;
import net.nigne.yzrproject.service.DirectorService;
import net.nigne.yzrproject.service.GenreService;
import net.nigne.yzrproject.service.MovieService;
import net.nigne.yzrproject.service.NoticeService;
import net.nigne.yzrproject.service.ReservationService;
import net.nigne.yzrproject.service.SupportNoticeService;
import net.nigne.yzrproject.service.UserCouponService;
import net.nigne.yzrproject.service.UserInfoService;

@Controller
public class YzrController {
   
   @Autowired
   private MovieService movie_service;
   @Autowired
   private ReservationService reservation_service;
   @Autowired
   private NoticeService notice_service;
   @Autowired
   private GenreService genre_service;
   @Autowired
   private ActorService actor_service;
   @Autowired
   private DirectorService director_service;
   @Autowired
   private SupportNoticeService noticeService;
   @Autowired
   private UserInfoService userInfoService;
   @Autowired
   private UserCouponService userCouponService;

   
   @RequestMapping(value = "", method = RequestMethod.GET)
   public ModelAndView homeA(Locale locale, Model model) throws Exception {

      return new ModelAndView("redirect:/index");
   
   }
   @RequestMapping(value = "/map", method = RequestMethod.GET)
   public ModelAndView homeB(Locale locale, Model model) throws Exception {

      return new ModelAndView("map");
   
   }
   @RequestMapping(value = "/user", method = RequestMethod.GET)
   public String userPage(Model model, HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession();
		String member_id = (String)session.getAttribute("member_id");
		
		if(member_id == "" || member_id == null){
			return "login";
		}else{
			MemberVO vo = userInfoService.getMemberInfo(member_id);
			long couponTotal = userCouponService.getNotUseCouponTotal(member_id);
			
			model.addAttribute("userInfo", vo);
			model.addAttribute("couponTotal", couponTotal);
			model.addAttribute("today", new Date());
			
			return "user/index";
		}

   }
   
   @RequestMapping(value = "/support", method = RequestMethod.GET)
   public ModelAndView supportPage() throws Exception {
      
      Criteria cri=new Criteria();
      cri.setPage(1);
      cri.setArticlePerPage(5);
      ModelAndView view=new ModelAndView();
      view.addObject("notice_list", noticeService.getNoticeList(cri));
      view.setViewName("support/index");
      return view;
   }
   
   @RequestMapping(value = "/index", method = RequestMethod.GET)
   public String index(HttpServletRequest request, Model model) throws Exception {
      HttpSession session = request.getSession();
      String member_id = (String)session.getAttribute("member_id");
      
      MovieVO rec_genre_movie = new MovieVO();   // ��õ�帣��ȭ
      MovieVO rec_actor_movie = new MovieVO();   // ��õ��쿵ȭ
      MovieVO rec_director_movie = new MovieVO(); // ��õ������ȭ
      
      if(member_id == "" || member_id == null){
         // �⺻��õ��ȭ
         List<MovieVO> rec_basic = movie_service.basicMovie();
         List<String> basic_title = new ArrayList<String>();
         
         if(!rec_basic.isEmpty()){
            basic_title.add("No.1");
            basic_title.add("No.2");
            basic_title.add("No.3");
            
            model.addAttribute("basic_title", basic_title);
            model.addAttribute("rec_basic", rec_basic);
         }else{
            basic_title.add("��ϵ� ��ȭ�� �����ϴ�");
            
            model.addAttribute("basic_title", basic_title);
            model.addAttribute("rec_basic", null);
         }
         
      }else{
         List<ReservationVO> list = reservation_service.getReservation_list(member_id);   // ���ų���
         
         List<MovieVO> rec_genre = null;         // ��õ�帣���
         List<MovieVO> rec_actor = null;         // ��õ�����
         List<MovieVO> rec_director = null;      // ��õ�������
         
         
         if(list.isEmpty()){
            rec_genre = movie_service.genreMovie(member_id);
            rec_actor = movie_service.actorMovie(member_id);
            rec_director = movie_service.directorMovie(member_id);
            List<MovieVO> rec_movie = new ArrayList<MovieVO>();
            
            if(!rec_genre.isEmpty() && !rec_actor.isEmpty() && !rec_director.isEmpty()){
               rec_movie.add(rec_genre.get(0));
               rec_movie.add(rec_actor.get(0));
               rec_movie.add(rec_director.get(0));
            
               model.addAttribute("rec_movie", rec_movie);
            }else{
               model.addAttribute("rec_movie", null);
            }
            
            List<String> rec_title = new ArrayList<String>();
            
            rec_title.add("No.1");
            rec_title.add("No.2");
            rec_title.add("No.3");
      
            model.addAttribute("rec_title", rec_title);
            
         }else{
            // ��õ��ȭ�� ���� ������ �ִ��� Ȯ���ϱ� ���� movie_id �迭
            List<String> list_movieId = new ArrayList<String>();
            
            for(int i=0; i<list.size(); i++){
               list_movieId.add(list.get(i).getMovie_id());
            }
            
            rec_genre = movie_service.genreMovie(member_id);         // ��õ�帣���
            rec_actor = movie_service.actorMovie(member_id);         // ��õ�����
            rec_director = movie_service.directorMovie(member_id);      // ��õ�������
            
            
            // movie_id �迭�� ��õ�帣��ȭ movie_id�� �ִ��� Ȯ��
            String gn = null;
            String an = null;
            String dn = null;
            List<String> rec_title = new ArrayList<String>();
            
            for(MovieVO vo : rec_genre){
                if(!list_movieId.contains(vo.getMovie_id())){
             	   gn = vo.getMovie_id();
             	   break;
                }
             }
            
            String genre = "";
            
            if(gn == null){
            	rec_genre_movie = movie_service.basicMovie().get(0);
            	rec_title.add("No.1");
            }else{
	            rec_genre_movie = movie_service.getMovie(gn);

	            genre = genre_service.getMovie_genre(rec_genre_movie.getMovie_id());
	            rec_title.add("�帣&nbsp<&nbsp" + genre + "&nbsp>");
            }
            
            // movie_id �迭�� ��õ��쿵ȭ movie_id�� �ִ��� Ȯ��
            for(MovieVO vo : rec_actor){
               if(!list_movieId.contains(vo.getMovie_id())){
            	   an = vo.getMovie_id();
            	   break;
               }
            }
            
            String a = "";
            List<String> actor = null;
            List<String> actor2 = null;
            
            if(an == null){
            	rec_actor_movie = movie_service.basicMovie().get(1);
            	rec_title.add("No.2");
            }else{
	            rec_actor_movie = movie_service.getMovie(an);
	            actor = actor_service.getMovie_actor(member_id);
	            actor2 = actor_service.getActor(rec_actor_movie.getMovie_id());
	            System.out.println(actor);
	            System.out.println(actor2);
	            line : for(int i=0; i<actor.size(); i++){
	            	if(actor2.contains(actor.get(i))){
	            		a = actor.get(i);
	            		break line;
	            	}
	            }
	            rec_title.add("���&nbsp<&nbsp" + a + "&nbsp>");
            }
            
            // movie_id �迭�� ��õ������ȭ movie_id�� �ִ��� Ȯ��
            for(MovieVO vo : rec_director){
            	if(!list_movieId.contains(vo.getMovie_id())){
             	   dn = vo.getMovie_id();
             	   break;
                }
             }
            
            String director = "";
            if(dn == null){
            	rec_director_movie = movie_service.basicMovie().get(2);
            	rec_title.add("No.3");
            }else{
	            rec_director_movie = movie_service.getMovie(dn);
	            director = director_service.getMovie_director(rec_director_movie.getMovie_id());
	            rec_title.add("����&nbsp<&nbsp" + director + "&nbsp>");
            }
            
            // ī�װ��� ��õ��ȭ ����Ʈ ���
            List<MovieVO> rec_movie = new ArrayList<MovieVO>();
            rec_movie.add(rec_genre_movie);
            rec_movie.add(rec_actor_movie);
            rec_movie.add(rec_director_movie);
         
            model.addAttribute("rec_movie", rec_movie);
            
            // ��õ��ȭ�� Ÿ��Ʋ
            model.addAttribute("rec_title", rec_title);
         }   
      }
 
	List<NoticeVO> notice_list = notice_service.getNotice();
	model.addAttribute("notice", notice_list);
	
	List<NoticeVO> event_list = notice_service.getEvent();
	model.addAttribute("event_list", event_list);
		
	return "index";
	}

}