import "../component-styles/loginMain.css";
import emailico from "../emailico.png";
import passico from "../passico.png";
import axios from "axios";

function App(props) {
  let url = "http://localhost:8080/api/v1/users/auth/login";
  let createPost = () => {
      axios({
        method: 'POST',
        url: 'http://localhost:8080/api/v1/users/auth/login',
        data: JSON.stringify({
          username: 'lebus',
          password: 'lebuspassword'
        }),
        headers: {
          'content-type': 'application/x-www-form-urlencoded;charset=utf-8'
        }
      }).catch(error => {
        console.log(error)
      })
  };
  return (
    <div className="loginMain">
      <div className="loginContainer">
        <button
          onClick={() => {
            props.FECresponse2();
            createPost();
          }}
          className="loginMainLoginButton"
        >
          Log In
        </button>
        <div className="loginMainButtonSeperator">- or -</div>
        <button
          onClick={() => props.FECresponse3()}
          className="loginMainSignUpButton"
        >
          Sign Up
        </button>

        <div className="loginEmailContainer">
          <div className="loginEmailContainerIconSpace">
            <img src={emailico} className="loginIcons"></img>
          </div>
          <input
            placeholder="Email"
            className="loginEmailContainerInputSpace"
          ></input>
        </div>
        <div className="loginPasswordContainer">
          <div className="loginPasswordContainerIconSpace">
            <img src={passico} className="loginIcons"></img>
          </div>
          <input
            placeholder="Password"
            type="password"
            className="loginPasswordContainerInputSpace"
          ></input>
        </div>
      </div>

      <div className="loginTitle">Welcome Back</div>
      <button onClick={() => props.FECresponse1()} className="loginBackButton">
        {"< Back"}
      </button>
    </div>
  );
}

export default App;
