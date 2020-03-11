package wang.bannong.gk5.test.mybatisx.velocity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class HelloController {

    @RequestMapping(value = "/bn", method = RequestMethod.GET)
    public ModelAndView index() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "bn");
        map.put("age", "29");
        return new ModelAndView("bn", map);
    }

}