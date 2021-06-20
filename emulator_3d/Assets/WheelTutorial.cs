using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.EventSystems;

public class WheelTutorial : MonoBehaviour, IDragHandler
{
    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        if (Input.mouseScrollDelta != Vector2.zero) {
            Debug.Log("Wheel");
            Destroy(gameObject);
        }
    }


    public void OnDrag(PointerEventData eventData)
    {
       
    }
}
