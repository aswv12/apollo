package com.ctrip.apollo.portal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctrip.apollo.portal.entity.Privilege;
import com.ctrip.apollo.portal.repository.PrivilegeRepository;

@Service
public class PrivilegeService {

  enum PrivilType {
    EDIT, REVIEW, RELEASE
  }

  @Autowired
  private PrivilegeRepository privilRepo;

  public boolean hasPrivilege(String appId, String name, PrivilType privilType) {
    Privilege privil = privilRepo.findByAppIdAndPrivilType(appId, privilType.name());
    if (privil != null && privil.getName().equals(name)) return true;
    return false;
  }

  public List<Privilege> listPrivileges(String appId) {
    return privilRepo.findByAppId(appId);
  }

  public Privilege setPrivilege(String appId, String name, PrivilType privilType) {
    Privilege privil = privilRepo.findByAppIdAndPrivilType(appId, privilType.name());
    if (privil == null) {
      privil = new Privilege();
      privil.setAppId(appId);
      privil.setPrivilType(privilType.name());
    }
    privil.setName(name);
    return privilRepo.save(privil);
  }
}
