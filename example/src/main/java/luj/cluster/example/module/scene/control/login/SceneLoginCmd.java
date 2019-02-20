package luj.cluster.example.module.scene.control.login;


import luj.cluster.example.module.scene.control.login.SceneLoginCmd.LoadResult;
import luj.cluster.example.module.scene.data.SceneDat;
import luj.cluster.example.module.scene.data.SceneObjectDat;
import luj.game.api.data.GameDataCommand;
import luj.game.api.data.GameDataLoad;
import org.springframework.stereotype.Component;

@Component
final class SceneLoginCmd implements GameDataCommand<LoadResult> {

  interface LoadResult {

//    @CreateIfAbsent
    SceneObjectDat playerObj();

    SceneDat scene();
  }

  @Component
  static final class Load implements GameDataLoad<LoadResult> {

    @Override
    public void load(Context<LoadResult> ctx) {
      ctx.load(LoadResult::playerObj)
          .load(SceneObjectDat::currentScene, LoadResult::scene);
    }
  }

  @Override
  public void execute(Context<LoadResult> ctx) {
    LoadResult data = ctx.getData();


    SceneDat scene = data.scene();

  }
}
