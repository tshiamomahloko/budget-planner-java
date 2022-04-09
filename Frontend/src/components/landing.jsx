import "../component-styles/landing.css";

function App(props) {
  return (
    <div className="landingMain">
      <img src={require("../logotmp.png")} className="landingLogo"></img>
      <div className="landingTitle">Budget App.</div>
      <button
        onClick={() => props.FECresponse1()}
        className="landingLoginButton"
      >
        Log In
      </button>
      <button
        onClick={() => props.FECresponse2()}
        className="landingSignUpButton"
      >
        Sign Up
      </button>
    </div>
  );
}

export default App;
