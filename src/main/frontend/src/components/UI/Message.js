import icons from './../../img/icons.svg';
const RecipeMessage = (props) => {
    return (
        <div className="message">
            {props.message.icon != null && <div>
                <svg>
                    <use href={`${icons}#icon-${props.message.icon}`}></use>
                </svg>
            </div>}
            <p>{props.message.message}</p>
        </div>
    );
}

export default RecipeMessage; 