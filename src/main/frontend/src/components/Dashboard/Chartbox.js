
import styles from './../../styles/components/chartbox.module.scss';
const ChartBox = (props) => {
    return (<div className={styles.chartbox} style={props.style}>
        This is a chart
    </div>)
}

export default ChartBox;