import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import javax.swing.*;
import java.awt.*;

enum ButtonState{
    addVertex,
    removeVertex,
    addEdge,
    Start,
    noButton
}

public class MainWindow extends JFrame {

    private JButton addVertex;
    private JButton removeVertex;
    private JButton addEdge;
    private JButton removeEdge;
    private JButton Start;
    private mxGraph graph;
    private ButtonState buttonState;


    public MainWindow(String hi){
        super(hi);
        buttonState = ButtonState.noButton;
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.X_AXIS));
        addVertex = new JButton("    Добавить вершину    ");
        removeVertex = new JButton("     Удалить вершину     ");
        addEdge = new JButton("       Добавить ребро       ");
        removeEdge = new JButton("        Удалить ребро        ");
        Start = new JButton("    Запустить алгоритм    ");
        graph = new mxGraph();

        addVertex.addActionListener(new addVertexButtonListener(this));
        removeVertex.addActionListener(new removeVertexButtonListener(this));
        addEdge.addActionListener(new addEdgeButtonListener(this));
        Start.addActionListener(new StartButtonListener(this));
        addMouseListener(new MainWindowMouseListener(graph, this));

        JPanel LeftPanel = new JPanel();
        LeftPanel.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
        LeftPanel.setLayout(new BoxLayout(LeftPanel, BoxLayout.Y_AXIS));
        LeftPanel.add(addVertex);
        LeftPanel.add(Box.createRigidArea(new Dimension(0,20)));
        LeftPanel.add(removeVertex);
        LeftPanel.add(Box.createRigidArea(new Dimension(0,20)));
        LeftPanel.add(addEdge);
        LeftPanel.add(Box.createRigidArea(new Dimension(0,20)));
        LeftPanel.add(removeEdge);
        LeftPanel.add(Box.createRigidArea(new Dimension(0,20)));
        LeftPanel.add(Start);
        LeftPanel.add(Box.createRigidArea(new Dimension(0,20)));

        String str = "Алгоритм Прима - алгоритмпостроения минимального  остовного дерева взвешен-ного связного неориентир-ованного графа. Алгоритм впервые был открыт в 1930году чешским математиком Войцехом Ярником, позже  открыт Робертом Примом в 1957 году, и, независимо от них, Дейкстрой в 1959 году.                             ";
        for(int i = 0 ; i < str.length() - 25; i+= 25){
            LeftPanel.add(new JLabel(str.substring(i, i+25)));
        }

        Object parent = graph.getDefaultParent();
        graph.getModel().beginUpdate();
        try{
            Object v1 = graph.insertVertex(parent, null, "A", 10, 10, 80, 30);
            Object v2 = graph.insertVertex(parent, null, "B", 240, 150, 80, 30);
            graph.insertEdge(parent, null, "12", v1, v2);
            graph.insertEdge(parent, null, "3", v1, v1);
        }
        finally {
            graph.getModel().endUpdate();
        }

        add(LeftPanel);
        add(new mxGraphComponent(graph));
    }

    public ButtonState getButtonState() {
        return buttonState;
    }

    public void setButtonState(ButtonState buttonState) {
        this.buttonState = buttonState;
    }
}
