package controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/public")
public class ApplicationController {

    private Dispatcher dispatcher;

    public ApplicationController(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void request(@RequestBody Query query) {
        dispatcher.dispatch(query);
    }

    @ResponseBody
    @RequestMapping(value = "/requestWithBody", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void requestWithBody(@RequestBody Query query) {
        new Dispatcher().dispatch(query);
    }

    @ResponseBody
    @RequestMapping(value = "/requestWithBody", method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void requestWithGet(@RequestBody Query query) {
        new Dispatcher().dispatch(query);
    }

}
