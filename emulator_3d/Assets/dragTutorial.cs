using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.EventSystems;

public class dragTutorial : MonoBehaviour, IBeginDragHandler, IEndDragHandler, IDragHandler
{
    // Start is called before the first frame update
    void Start()
    {
        
    }
    Vector3 mouseDownPos;
    bool mouseDown = false;
    // Update is called once per frame
    void Update()
    {
        if (Input.GetMouseButtonDown(0))
        {
            mouseDownPos = Input.mousePosition;
            mouseDown = true;
        }
        if (Input.GetMouseButtonUp(0))
        {
            mouseDown = false;
        }
        if (mouseDown && Input.mousePosition != mouseDownPos) {
            Destroy(gameObject);
        }
        
    }


    public void OnBeginDrag(PointerEventData eventData)
    {
      
    }

    public void OnEndDrag(PointerEventData eventData)
    {
     
    }

    public void OnDrag(PointerEventData eventData)
    {
        Destroy(gameObject);
    }

}
