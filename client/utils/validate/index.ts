function emailValidate(email: string) {
  const reg = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/
  if (email.length >= 5) {
    if (reg.test(email) == false) {
      return true
    }
  }
  return false
}

function passwordValidate(password: string) {
  const reg = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/
  if (reg.test(password) == false) {
    return true
  }
  return false
}

function passwordConfirmValidate(password: string, passwordConfirm: string) {
  if (password !== passwordConfirm) {
    return true
  }
  return false
}

function phoneValidate(phoneNumber: string) {
  var patternPhone = /01[016789]-[^0][0-9]{2,3}-[0-9]{3,4}/

  if (patternPhone.test(phoneNumber) === false || phoneNumber.length > 14) {
    return true
  }
  return false
}

const Validate = {
  emailValidate,
  passwordValidate,
  passwordConfirmValidate,
  phoneValidate,
}
export {Validate}
