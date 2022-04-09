import "../component-styles/signUpMain.css";
import userico from "../userico.png";
import emailico from "../emailico.png";
import passico from "../passico.png";

function App(props) {
  return (
    <div className="signUpMain">
      <div className="signUpContainer">
        <button
          onClick={() => props.FECresponse2()}
          className="signUpMainLoginButton"
        >
          Log In
        </button>
        <div className="signUpMainButtonSeperator">- or -</div>
        <button
          onClick={() => props.FECresponse3()}
          className="signUpMainSignUpButton"
        >
          Sign Up
        </button>

        <div className="signUpNameContainer">
          <div className="signUpNameContainerIconSpace">
            <img src={userico} className="signUpIcons"></img>
          </div>
          <input
            placeholder="Name"
            className="signUpNameContainerInputSpace"
          ></input>
        </div>
        <div className="signUpEmailContainer">
          <div className="signUpEmailContainerIconSpace">
            <img src={emailico} className="signUpIcons"></img>
          </div>
          <input
            placeholder="Email"
            className="signUpEmailContainerInputSpace"
          ></input>
        </div>
        <div className="signUpPasswordContainer">
          <div className="signUpPasswordContainerIconSpace">
            <img src={passico} className="signUpIcons"></img>
          </div>
          <input
            placeholder="Password"
            type="password"
            className="signUpPasswordContainerInputSpace"
          ></input>
        </div>
      </div>

      <div className="signUpTitle">Create Account</div>
      <button onClick={() => props.FECresponse1()} className="signUpBackButton">
        {"< Back"}
      </button>
    </div>
  );
}

export default App;
