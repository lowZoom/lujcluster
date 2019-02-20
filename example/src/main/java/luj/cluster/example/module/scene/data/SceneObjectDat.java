package luj.cluster.example.module.scene.data;

import luj.data.type.JRef;
import luj.data.type.JStr;

public interface SceneObjectDat {

  JStr id();

  JRef<SceneDat> currentScene();
}
