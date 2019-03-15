package luj.cluster.example.module.scene.control.login;


import luj.cluster.example.module.scene.control.login.SceneLoginCmd.LoadResult;
import luj.cluster.example.module.scene.data.SceneDat;
import luj.cluster.example.module.scene.data.SceneObjectDat;
import luj.game.api.data.PlayerDataCommand;
import luj.game.api.data.PlayerDataLoad;
import org.springframework.stereotype.Component;

@Component
public class SceneLoginCmd implements PlayerDataCommand<LoadResult> {

  interface LoadResult {

//    @CreateIfAbsent
    SceneObjectDat playerObj();

    SceneDat scene();
  }

  @Component
  static final class Load implements PlayerDataLoad<LoadResult> {

    @Override
    public void load(Context<LoadResult> ctx) {
      ctx.load(LoadResult::playerObj)
          .join(SceneObjectDat::currentScene, LoadResult::scene);
    }
  }

  @Override
  public void execute(Context ctx) {
    LoadResult data = ctx.data(this);


    SceneDat scene = data.scene();

  }
}
