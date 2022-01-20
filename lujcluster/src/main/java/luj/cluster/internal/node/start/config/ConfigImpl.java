package luj.cluster.internal.node.start.config;

import com.google.common.collect.ImmutableList;
import java.util.List;
import luj.cluster.api.ClusterSession;
import luj.cluster.internal.node.start.ClusterNodeStarter;

final class ConfigImpl implements ClusterSession.Start, ClusterNodeStarter.Config {

  @Override
  public ClusterSession.Start selfHost(String val) {
    _selfHost = val;
    return this;
  }

  @Override
  public ClusterSession.Start selfPort(int val) {
    _selfPort = val;
    return this;
  }

  @Override
  public ClusterSession.Start selfPortAkka(int val) {
    _selfPortAkka = val;
    return this;
  }

  @Override
  public ClusterSession.Start selfName(String val) {
    _selfName = val;
    return this;
  }

  @Override
  public ClusterSession.Start selfTags(List<String> val) {
    _selfTags = val;
    return this;
  }

  @Override
  public ClusterSession.Start discoveryAkkaSeed(List<String> val) {
    _discoveryAkkaSeed = val;
    return this;
  }

  @Override
  public ClusterSession.Start discoveryConsulHost(String val) {
    _discoveryConsulHost = val;
    return this;
  }

  @Override
  public ClusterSession.Start discoveryConsulPort(int val) {
    _discoveryConsulPort = val;
    return this;
  }

  @Override
  public ClusterSession.Start startParam(Object val) {
    _startParam = val;
    return this;
  }

  /////////////////////////////////////

  @Override
  public String selfHost() {
    return _selfHost;
  }

  @Override
  public int selfPort() {
    return _selfPort;
  }

  @Override
  public int selfPortAkka() {
    return _selfPortAkka;
  }

  @Override
  public String selfName() {
    return _selfName;
  }

  @Override
  public List<String> selfTags() {
    return _selfTags;
  }

  @Override
  public List<String> discoveryAkkaSeed() {
    return _discoveryAkkaSeed;
  }

  @Override
  public String discoveryConsulHost() {
    return _discoveryConsulHost;
  }

  @Override
  public int discoveryConsulPort() {
    return _discoveryConsulPort;
  }

  @Override
  public Object startParam() {
    return _startParam;
  }

  /////////////////////////////////////

  String _selfHost;
  int _selfPort;
  int _selfPortAkka;

  String _selfName;
  List<String> _selfTags;

  List<String> _discoveryAkkaSeed = ImmutableList.of();
  String _discoveryConsulHost;
  int _discoveryConsulPort;

  Object _startParam;
}
