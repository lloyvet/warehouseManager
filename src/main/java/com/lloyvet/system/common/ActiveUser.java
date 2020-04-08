package com.lloyvet.system.common;

import com.lloyvet.system.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActiveUser implements Serializable {

        private User user;
        private List<String> roles;
        private List<String> permissions;
}
