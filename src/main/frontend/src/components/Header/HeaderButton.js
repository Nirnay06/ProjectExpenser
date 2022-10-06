import { Button } from 'antd';
import { PlusCircleOutlined } from '@ant-design/icons';
import useHttp from '../../hooks/useHttp';


const HeaderButton = (props) => {
    return (
        <>
            <Button type="primary" shape="round" size={'small'} icon={<PlusCircleOutlined />} onClick={props.onClick}>
                Record
            </Button>
        </>
    )
}
export default HeaderButton;