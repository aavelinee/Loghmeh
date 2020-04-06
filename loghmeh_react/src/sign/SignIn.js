function SignInForm() {
    return (
      <div class="container-sm border">
        <div class="form-inline">
            <input 
            type="email" 
            name="email" 
            class="sign-up-form form-control mb-2 mr-sm-2" 
            placeholder="ایمیل">
            </input>
            <input 
            type="password" 
            name="password" 
            class="sign-up-form form-control mb-2 mr-sm-2" 
            placeholder="رمز عبور">
            </input>
        </div>
        <div class="sub">
          <input type="submit" value="ثبت نام" class="btn btn-primary pull-center"></input>
        </div>
      </div>
    );
  }
  