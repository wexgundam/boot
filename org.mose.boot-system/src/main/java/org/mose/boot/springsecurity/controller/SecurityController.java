package org.mose.boot.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 登录、注销、未授权异常处理
 *
 * Created by Administrator on 2017/8/1.
 */
@Controller
@RequestMapping("/")
public class SecurityController {
//    @Autowired
//    private SecurityService securityService;
//    @Autowired
//    private SessionRegistry sessionRegistry;
//    @Autowired
//    private SpringSessionRedisService springSessionRedisService;
//
//    @RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
//    public ModelAndView accessDenied(HttpServletRequest request) {
//        ModelAndView modelAndView = new ModelAndView();
//        AccessDeniedException exception = (AccessDeniedException) request.getAttribute(WebAttributes.ACCESS_DENIED_403);
//        modelAndView.getModelMap().addAttribute("errorDetails", exception.getMessage());
//        StringWriter stringWriter = new StringWriter();
//        exception.printStackTrace(new PrintWriter(stringWriter));
//        modelAndView.getModelMap().addAttribute("errorTrace", stringWriter.toString());
//        modelAndView.setViewName("accessDenied");
//        return modelAndView;
//    }
//
//    @RequestMapping(value = "/activeSessionCount", method = RequestMethod.GET)
//    @ResponseBody
//    public String activeSessionCount() throws Exception {
////        return "Current active session count is " + securityService.getActiveSessionCount();
//        for (String key : springSessionRedisService.getAllKeys()) {
//            for (Session session : springSessionRedisService.getSessionsByKey(key)) {
//                if (session != null) {
//                    String sessionId = session.getId();
//                    User user = (User) sessionRegistry.getSessionInformation(sessionId).getPrincipal();
//                    System.out.println(user.getUsername());
//                }
//            }
//        }
//        return "Current active session count is " + springSessionRedisService.getAllKeys().size();
//    }
}
