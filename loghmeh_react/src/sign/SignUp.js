function SignUpForm() {
	return (
	  <div className = "container-sm border">
		<div className = "form-inline">
		  <input
			type = "text" name = "firstname"
			className = "sign-up-form form-control mb-2 mr-sm-2"
			placeholder = "نام">
		  </input>
		  <input
			type = "text"
			name = "lastname"
			className = "sign-up-form form-control mb-2 mr-sm-2"
			placeholder = "نام خانوادگی">
		  </input>
		  <input
			type = "email"
			name = "email"
			className = "sign-up-form form-control mb-2 mr-sm-2"
			placeholder = "ایمیل">
		  </input>
		  <input
			type = "text"
			name = "username"
			className = "sign-up-form form-control mb-2 mr-sm-2"
			placeholder = "نام کاربری">
		  </input>
		  <input
			type = "password"
			name = "password"
			className = "sign-up-form form-control mb-2 mr-sm-2"
			placeholder = "رمز عبور">
		  </input>
		</div>
		<div className = "sub" >
		  <input type = "submit" value = "ثبت نام" className = "btn btn-primary pull-center"></input>
		</div>
	  </div>
	);
  }