package controllers;

import play.data.DynamicForm;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;

public class GameController extends Controller
{
    final private String CHARACTER_NAME_SESSION_KEY = "CHARACTER_NAME";
    private FormFactory formFactory;
    private String charName;

    @Inject
    public GameController(FormFactory formFactory)
    {
        this.formFactory = formFactory;
    }

    public Result getCreateCharacter()
    {
        return ok(views.html.createcharacter.render());
    }

    public Result postCreateCharacter(Http.Request request)
    {
        DynamicForm form = formFactory.form().bindFromRequest(request);
        String characterName = form.get("characterName");
        this.charName = characterName;
        return ok(views.html.start.render()).addingToSession(request, CHARACTER_NAME_SESSION_KEY, characterName);
    }

    public Result getStart()
    {
        return ok(views.html.start.render());
    }

    public Result postStart()
    {
        return ok(views.html.outsidecabin.render());
    }

    public Result postForest(Http.Request request)
    {
        String characterName = request.session().getOptional(CHARACTER_NAME_SESSION_KEY).orElse("Unknown");
        //String characterName = charName;
        return ok(views.html.forest.render(characterName));
    }
}
