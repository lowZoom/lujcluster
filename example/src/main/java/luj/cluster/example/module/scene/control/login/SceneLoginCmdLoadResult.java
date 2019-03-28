package luj.cluster.example.module.scene.control.login;

import luj.cluster.example.module.scene.data.SceneDat;
import luj.cluster.example.module.scene.data.SceneObjectDat;

@Deprecated
public class SceneLoginCmdLoadResult implements SceneLoginCmd.LoadResult {

  @Override
  public SceneObjectDat playerObj() {
    return _sceneObject;
  }

  @Override
  public SceneDat scene() {
    return null;
  }

  public void setSceneObject(SceneObjectDat sceneObject) {
    _sceneObject = sceneObject;
  }

  private SceneObjectDat _sceneObject;
}
