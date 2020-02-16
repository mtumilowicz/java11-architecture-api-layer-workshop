package app.x;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class XController {

    @Autowired
    private XService xService;


}
