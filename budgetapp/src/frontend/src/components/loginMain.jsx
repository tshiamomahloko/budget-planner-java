import "../component-styles/loginMain.css";
import emailico from '../emailico.png';
import passico from '../passico.png';

function App(props) {
  return (
    <div className="loginMain">
      <div className="loginContainer">
        <button
          onClick={() => props.FECresponse2()}
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
