package org.cn.kaito.auth.Service.ServiceImpl;

import org.apache.ibatis.reflection.wrapper.ObjectWrapper;
import org.cn.kaito.auth.DTO.LogDTO;
import org.cn.kaito.auth.DTO.OwnerDTO;
import org.cn.kaito.auth.DTO.UserDTO;
import org.cn.kaito.auth.Dao.Entity.OperationEntity;
import org.cn.kaito.auth.Dao.Entity.SubTaskEntity;
import org.cn.kaito.auth.Dao.Repository.LogRepository;
import org.cn.kaito.auth.Dao.Repository.TaskRepository;
import org.cn.kaito.auth.Dao.Repository.UserRepository;
import org.cn.kaito.auth.Exception.CustomerException;
import org.cn.kaito.auth.Service.LogService;
import org.cn.kaito.auth.Utils.DateStringUtil;
import org.cn.kaito.auth.Utils.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    LogRepository logRepository;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<LogDTO> getLogs(String uid, String projectID, int page) throws CustomerException {
        List<LogDTO> logDTOs = new ArrayList<>();
        if (ValidateUserAuth(uid,projectID)){
            List<OperationEntity> operations =  logRepository.findAllByProjectID(projectID);
            for (OperationEntity operationEntity:operations){
                LogDTO logDTO = new LogDTO();
                logDTO.setAction(operationEntity.getOperationName());
                OwnerDTO ownerDTO = new OwnerDTO();
                UserDTO userDTO = userRepository.getUserDTOsByID(operationEntity.getOperatorID())
                        .orElseThrow(()->new CustomerException(StatusEnum.CANT_FIND_USER));
                ownerDTO.setName(userDTO.getUsername());
                ownerDTO.setId(userDTO.getUserID());
                logDTO.setOperator(ownerDTO);
                logDTO.setTime(operationEntity.getTime());
                logDTOs.add(logDTO);
            }
        }
        return logDTOs;
    }

    /**
     *
     * @return 如果是true就表示是管理员或者是在项目中进行 或者被委派的员工，
     * 如果不是项目中的员工的话，就抛出异常
     */
    private boolean ValidateUserAuth(String uid,String projectID) throws CustomerException {
        UserDTO userDTO  = userRepository.getUserDTOsByID(uid)
                .orElseThrow(()->new CustomerException(StatusEnum.CANT_FIND_USER));
        if (userDTO.getType().equals("ROLE_ADMIN")){
            return true;
        }else {
            Optional<SubTaskEntity> subTaskEntity = taskRepository.findAllByExecutorAndAndProjectID(uid,projectID);
            Optional<String> s = taskRepository.findEntrustWorkByEntrustorandProjectID(uid,projectID);
            if (subTaskEntity.isEmpty() && s.isEmpty()){
                throw new CustomerException(StatusEnum.DONT_HAVE_PERMISSION_GETLOG);
            }
            return true;
        }
    }
}
