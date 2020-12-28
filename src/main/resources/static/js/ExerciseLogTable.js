class ExerciseLogTable extends React.Component {
    
    constructor(props) {
        super(props);
        this.state = {exerciseData: []}
    }
    
    componentDidMount() {
        fetch('http://localhost:8080/log')
        .then(response => response.json()
        .then(data => {
            console.log(data);
            this.setState({exerciseData: data});
        }));
    }
    
    render() {
        const rows = this.state.exerciseData.map(logs => <tr><td>{logs.name}</td><td>{logs.duration}</td><td>{logs.date.replaceAll("-","/")}</td></tr>);
        return (
            <div className="col-auto">
                <table className="table table-striped table-bordered table-responsive">
                    <tr>
                        <th>Exercise</th>
                        <th>Duration (minutes)</th>
                        <th>Date</th>
                    </tr>
                    {rows}
                </table>
            </div>
        );
    }
}

const exerciseTableContainer = document.getElementById("test");
ReactDOM.render(React.createElement(ExerciseLogTable), exerciseTableContainer);