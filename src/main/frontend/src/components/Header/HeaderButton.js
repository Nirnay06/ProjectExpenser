import { Button } from 'antd';
import { PlusCircleOutlined } from '@ant-design/icons';
import useHttp from '../../hooks/useHttp';


const HeaderButton = (props) => {
    const { sendRequest } = useHttp();
    const onButtonClick = async () => {
        sendRequest({
            url: "/api/advance"
        }, (data) => { console.log(data); })
    }
    return (
        <>
            <Button type="primary" shape="round" size={'small'} icon={<PlusCircleOutlined />} onClick={onButtonClick}>
                Record
            </Button>
        </>
    )
}
export default HeaderButton;