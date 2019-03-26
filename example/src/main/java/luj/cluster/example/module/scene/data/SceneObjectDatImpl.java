package luj.cluster.example.module.scene.data;

@Deprecated
public class SceneObjectDatImpl implements SceneObjectDat {

  @Override
  public String id() {
    return "id";
  }

  @Override
  public SceneDat currentScene() {
    return new SceneDatImpl();
  }
}
