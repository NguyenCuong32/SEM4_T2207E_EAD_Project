package com.fai.brofee_fe.service;

import com.fai.brofee_fe.dto.UserCreateDTO;
import com.fai.brofee_fe.dto.UserDTO;
import com.fai.brofee_fe.dto.UserDetail.Referrer;
import com.fai.brofee_fe.dto.UserDetail.RootDetailUserDTO;
import com.fai.brofee_fe.dto.UserDetail.UserDetailDTO;
import com.fai.brofee_fe.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@AllArgsConstructor
public class UserService implements IUserService{

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public void createUser(UserCreateDTO userCreateDTO) {

    }

    @Override
    public Optional<UserDTO> getUserByCodeOrPhone(String codeOrPhone) {
        return userRepository.findByCodeOrPhone(codeOrPhone, codeOrPhone).map(user -> modelMapper.map(user, UserDTO.class));
    }


    @Override
    public List<UserDTO> SearchUser(int page, int size, String search) {
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
            List<Object[]> users = userRepository.findUser(page ,size ,search);
            List<UserDTO> userDTOs = new ArrayList<>();

            for (int i = 0; i <users.size() ; i++) {

                String code = users.get(i)[0] == null ? null : users.get(i)[0].toString();
                Long id = users.get(i)[1] == null ? null : Long.parseLong(users.get(i)[1].toString());
                String phone =users.get(i)[2] == null ? null: users.get(i)[2].toString();
                String name = users.get(i)[3] == null ? null : users.get(i)[3].toString();
                String email = users.get(i)[4] == null ? null : users.get(i)[4].toString();
                String address = users.get(i)[5] == null ? null: users.get(i)[5].toString();
                String avatar = users.get(i)[6]  == null ? null : users.get(i)[6].toString();
                LocalDateTime created_at = users.get(i)[7] == null ? null : LocalDateTime.parse(users.get(i)[7].toString(),formatter);
                LocalDateTime updated_at = users.get(i)[8] == null ? null :LocalDateTime.parse( users.get(i)[8].toString(),formatter);
                LocalDateTime deleted_at = users.get(i)[9] == null ? null : LocalDateTime.parse(users.get(i)[9].toString(),formatter);
                BigDecimal totalTransaction =users.get(i)[10] == null ? null : new BigDecimal(users.get(i)[10].toString());
                UserDTO userDTO = new UserDTO(id,code,phone,name,email,avatar,address,totalTransaction,created_at,updated_at,deleted_at);
                userDTOs.add(userDTO);
            }
            return userDTOs;
        }catch (Exception exception){
            List<UserDTO> users = new ArrayList<>();
            return users;
        }
    }


    @Override
    public long CountSearchUser(String search) {
        Long data = userRepository.countFindUser(search);
        return  data;
    }

    @Override
    public RootDetailUserDTO detailUser(Long id) {


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        RootDetailUserDTO detailUser = new RootDetailUserDTO();
        Object[] inforDetail =(Object[]) userRepository.finDetailUser(id);
        // get thông tin ngươi dùng
        Long userId = inforDetail[0] == null ? null : Long.parseLong(inforDetail[0].toString());
        String code = inforDetail[1] == null ? null : inforDetail[1].toString();
        String phone = inforDetail[2] == null ? null : inforDetail[2].toString();
        String email = inforDetail[3] == null ? null : inforDetail[3].toString();
        String name = inforDetail[4] == null ? null : inforDetail[4].toString();
        String address = inforDetail[5] == null ? null : inforDetail[5].toString();
        String avatar = inforDetail[6] == null ? null : inforDetail[6].toString();
        LocalDateTime created_at = inforDetail[7] == null ? null : LocalDateTime.parse(inforDetail[7].toString(),formatter);
        LocalDateTime updated_at = inforDetail[8] == null ? null :LocalDateTime.parse( inforDetail[8].toString(),formatter);
        LocalDateTime deleted_at = inforDetail[9] == null ? null : LocalDateTime.parse(inforDetail[9].toString(),formatter);
        String role_name = inforDetail[10] == null ? null : inforDetail[10].toString();
        Long referrer_id = inforDetail[11] == null ? null : Long.parseLong(inforDetail[11].toString());
        String referrer_name = inforDetail[12] == null ? null : inforDetail[12].toString();
        String referrer_avatar = inforDetail[13] == null ? null : inforDetail[13].toString();
        detailUser.setUser(new UserDetailDTO(
                userId,
                code,
                phone,
                email,
                name,
                address,
                avatar,
                created_at,
                updated_at,
                deleted_at,
                role_name,
                referrer_id,
                referrer_name,
                referrer_avatar
        ));

        // danh sách người dùng đã giới thiệu
        List<Object> ReferralsListRepo = userRepository.findReferralsListForUser(id);
        List<Referrer> ReferralsList = new ArrayList<>();

        for (int i = 0; i <ReferralsListRepo.size() ; i++) {
            Object[] ReferralsObject =(Object[]) ReferralsListRepo.get(i);

             id = ReferralsObject[0] == null ? null : Long.parseLong(ReferralsObject[0].toString());
             name = ReferralsObject[1] == null ? null : ReferralsObject[1].toString();
             avatar = ReferralsObject[2]  == null ? null : ReferralsObject[2].toString();
             created_at = ReferralsObject[3] == null ? null : LocalDateTime.parse(ReferralsObject[3].toString(),formatter);
            ReferralsList.add(new Referrer(id,name,avatar,created_at));
        }
        detailUser.setReferralsList(ReferralsList);
        return detailUser;
    }

}
