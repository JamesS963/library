package com.springproject.library.form;

import com.springproject.library.model.Authority;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorityForm {
	private Authority authority;
	private boolean checked;
}
